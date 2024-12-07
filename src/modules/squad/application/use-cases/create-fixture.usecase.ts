import {Inject, Injectable} from '@nestjs/common'
import {Fixture} from '../../domain/models/fixture'
import {FixtureCriteria} from '../../domain/models/fixture-criteria'
import {LineUp} from '../../domain/models/lineup'
import {PlayerForFixture} from '../../domain/models/player-for-fixture'
import {FixtureDatabaseOutPort} from '../ports/out/fixture-database-out.port'
import {GetLatestFixture} from './get-latest-fixture.usecase'
import {DeleteFixture} from './delete-fixture.usecase'

@Injectable()
export class CreateFixture {
  constructor(
    @Inject(FixtureDatabaseOutPort.name)
    private readonly fixtureDatabaseOutPort: FixtureDatabaseOutPort,
    private readonly getLatestFixture: GetLatestFixture,
    private readonly deleteFixture: DeleteFixture
  ) {}

  async execute(criteria: FixtureCriteria): Promise<Fixture> {
    await this.deleteLastFixture(criteria)

    this.setRandomSequence(criteria.players, criteria.priorityPlayers)

    const lineUpSquad = this.createBySequence(criteria)

    let lineUps: LineUp[] = []
    if (lineUpSquad.length >= 2) {
      lineUps = this.balanceTwoLineUpsByLevel(lineUpSquad)
    } else {
      lineUps.push(this.createLineUp('Squad 1', lineUpSquad[0]))
    }

    return this.fixtureDatabaseOutPort.create(
      new Fixture({
        squadId: criteria.squadId,
        lineUps: lineUps,
        createdAt: new Date().toISOString()
      })
    )
  }

  private async deleteLastFixture(criteria: FixtureCriteria) {
    const latestFixture = await this.getLatestFixture.execute(criteria.squadId)
    if (!latestFixture) {
      return
    }

    const fixtureDate = new Date(latestFixture.createdAt)
    const lastFixtureDay = `${fixtureDate.getMonth() + 1}-${fixtureDate.toISOString().split('T')[0].split('-')[2]}`

    const todayDate = new Date()
    const today = `${todayDate.getMonth() + 1}-${todayDate.toISOString().split('T')[0].split('-')[2]}`

    const hasPreviousFixtureOnSameDay = lastFixtureDay === today

    if (hasPreviousFixtureOnSameDay) {
      await this.deleteFixture.execute(latestFixture.id)
    }
  }

  private balanceTwoLineUpsByLevel(
    lineUpSquad: PlayerForFixture[][]
  ): LineUp[] {
    const jointLineUps = lineUpSquad[0].concat(lineUpSquad[1])

    const playersByPosition = jointLineUps.reduce((map, player) => {
      if (!map[player.position]) {
        map[player.position] = [player]
      } else {
        map[player.position].push(player)
      }
      return map
    }, {})

    const balancedTwoSquads = {
      0: {players: [], level: 0},
      1: {players: [], level: 0}
    }

    Object.keys(playersByPosition).forEach((position) => {
      playersByPosition[position].sort((a, b) => b.level - a.level)

      playersByPosition[position].forEach((player) => {
        if (balancedTwoSquads[0].level > balancedTwoSquads[1].level) {
          balancedTwoSquads[1].players.push(player)
          balancedTwoSquads[1].level += player.level
        } else {
          balancedTwoSquads[0].players.push(player)
          balancedTwoSquads[0].level += player.level
        }
      })
    })

    const diff =
      balancedTwoSquads[0].players.length - balancedTwoSquads[1].players.length
    if (diff > 0) {
      balancedTwoSquads[0].players.sort((a, b) => a.level - b.level)
      for (let i = 0; i < diff; i++) {
        balancedTwoSquads[1].players.push(balancedTwoSquads[0].players[0])
        balancedTwoSquads[0].players.splice(0, 1)
      }
    } else if (diff < 0) {
      balancedTwoSquads[1].players.sort((a, b) => a.level - b.level)
      for (let i = 0; i < Math.abs(diff); i++) {
        balancedTwoSquads[0].players.push(balancedTwoSquads[0].players[0])
        balancedTwoSquads[1].players.splice(0, 1)
      }
    }

    const twoLineUps = [
      this.createLineUp('Lineup 1', balancedTwoSquads[0].players),
      this.createLineUp('Lineup 2', balancedTwoSquads[1].players)
    ]

    if (lineUpSquad.length > 2) {
      for (let i = 2; i < lineUpSquad.length; i++) {
        twoLineUps.push(this.createLineUp(`Lineup ${i + 1}`, lineUpSquad[i]))
      }
    }

    return twoLineUps
  }

  private createBySequence(criteria: FixtureCriteria): PlayerForFixture[][] {
    let lineUpAmount = Math.floor(
      criteria.players.length / criteria.amountPlayersInLineUp
    )
    const lineUpSquad = []
    for (let i = 0; i < lineUpAmount; i++) {
      const squad = []

      for (let j = 0; j < criteria.amountPlayersInLineUp; j++) {
        squad.push(criteria.players[0])
        criteria.players.splice(0, 1)
      }

      lineUpSquad.push(squad)
    }

    const bench = []
    criteria.players.forEach((player) => {
      bench.push(player)
    })

    if (bench.length > 0) {
      lineUpSquad.push(bench)
    }

    return lineUpSquad
  }

  private setRandomSequence(
    players: PlayerForFixture[],
    priotityPlayers?: PlayerForFixture[]
  ) {
    for (var i = players.length - 1; i > 0; i--) {
      var j = Math.floor(Math.random() * i)
      var temp = players[i]
      players[i] = players[j]
      players[j] = temp
    }

    if (priotityPlayers?.length > 0) {
      players.unshift(...priotityPlayers)
    }

    players.forEach((player, index) => {
      player.sequence = index + 1
    })
  }

  private createLineUp(name: string, players: PlayerForFixture[]): LineUp {
    return new LineUp({
      name: name,
      level: players.reduce((a, b) => a + b.level, 0),
      players: players
    })
  }
}
