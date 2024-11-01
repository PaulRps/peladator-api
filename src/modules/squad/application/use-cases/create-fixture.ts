import {Inject, Injectable} from '@nestjs/common'
import {FixtureCriteria} from '../../domain/fixture-criteria'
import {PlayerForFixture} from '../../domain/player-for-fixture'
import {Fixture} from '../../domain/fixture'
import {LineUp} from '../../domain/lineup'
import {
  FIXTURE_DATABASE_OUT_PORT,
  FixtureDatabaseOutPort
} from '../ports/out/fixture-database-out.port'

@Injectable()
export class CreateFixture {
  constructor(
    @Inject(FIXTURE_DATABASE_OUT_PORT)
    private readonly fixtureDatabaseOutPort: FixtureDatabaseOutPort
  ) {}

  execute(criteria: FixtureCriteria): Promise<Fixture> {
    this.setRandomSequence(criteria.players)

    const lineUpSquad = this.createBySequence(criteria)

    const squads = this.balanceSquads(lineUpSquad)

    return this.fixtureDatabaseOutPort.create(
      new Fixture({
        squadId: criteria.squadId,
        lineUps: squads,
        createdAt: new Date().toISOString()
      })
    )
  }

  private balanceSquads(lineUpSquad: PlayerForFixture[][]): LineUp[] {
    const jointSquads = lineUpSquad[0].concat(lineUpSquad[1])

    const playersByPosition = jointSquads.reduce((map, player) => {
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

    const squads = [
      new LineUp({
        name: 'Squad 1',
        level: balancedTwoSquads[0].players.reduce((a, b) => a + b.level, 0),
        players: balancedTwoSquads[0].players
      }),
      new LineUp({
        name: 'Squad 2',
        level: balancedTwoSquads[1].players.reduce((a, b) => a + b.level, 0),
        players: balancedTwoSquads[1].players
      })
    ]

    if (lineUpSquad.length > 2) {
      for (let i = 2; i < lineUpSquad.length; i++) {
        squads.push(
          new LineUp({
            name: `Squad ${i + 1}`,
            level: lineUpSquad[i].reduce((a, b) => a + b.level, 0),
            players: lineUpSquad[i]
          })
        )
      }
    }

    return squads
  }

  private createBySequence(criteria: FixtureCriteria): PlayerForFixture[][] {
    let squadsAmount = Math.floor(
      criteria.players.length / criteria.amountPlayersInLineUp
    )
    const lineUpSquad = []
    for (let i = 0; i < squadsAmount; i++) {
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

  private setRandomSequence(players: PlayerForFixture[]) {
    for (var i = players.length - 1; i > 0; i--) {
      var j = Math.floor(Math.random() * i)
      var temp = players[i]
      players[i] = players[j]
      players[j] = temp
    }

    players.forEach((player, index) => {
      player.sequence = index + 1
    })

    return players
  }
}
