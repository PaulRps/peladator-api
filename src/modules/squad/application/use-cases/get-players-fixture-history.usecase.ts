import {Inject, Injectable} from '@nestjs/common'
import {Fixture} from '../../domain/models/fixture'
import {PlayerId} from '../../domain/models/player'
import {
  FixtureHistory,
  PlayerFixtureHistory
} from '../../domain/models/player-fixture-history'
import {FixtureDatabaseOutPort} from '../ports/out/fixture-database-out.port'
import {PlayerDatabaseOutPort} from '../ports/out/player-database-out.port'
import {SquadExists} from './squad-exists.usecase'

@Injectable()
export class GetPlayersFixtureHistory {
  constructor(
    @Inject(PlayerDatabaseOutPort.name)
    private readonly playerOutPort: PlayerDatabaseOutPort,
    @Inject(FixtureDatabaseOutPort.name)
    private readonly fixtureDatabaseOutPort: FixtureDatabaseOutPort,
    private readonly squadExists: SquadExists
  ) {}

  async execute(
    playerIds: PlayerId[],
    squadId: string
  ): Promise<PlayerFixtureHistory[]> {
    const squadExists = await this.squadExists.execute(squadId)
    if (!squadExists) {
      throw new Error('Squad not found')
    }

    const playersMap = (
      await this.playerOutPort.getBy(squadId, playerIds)
    )?.reduce((map, player) => {
      map[player.id] = new PlayerFixtureHistory({
        id: player.id,
        squadId: player.squadId,
        name: player.name,
        position: player.position,
        level: player.level
      })
      return map
    }, {})

    const fixtures = await this.fixtureDatabaseOutPort.filterFixture({
      squadId: squadId,
      startDate: `${new Date().getFullYear()}-01-01 00:00:00`,
      endDate: `${new Date().getFullYear()}-12-31 23:00:59`
    })

    const historyMap = {}
    fixtures.forEach((fixture) => {
      fixture.lineUps.forEach((lineUp) => {
        lineUp.players.forEach((player) => {
          const p = playersMap[player.id]
          if (p) {
            if (historyMap[p.id]) {
              this.updateHistory(historyMap, p, fixture)
            } else {
              this.addHistory(historyMap, p, fixture)
            }
          }
        })
      })
    })

    const history: PlayerFixtureHistory[] = Object.values(historyMap)
    return history?.length > 0 ? history : Object.values(playersMap)
  }

  private addHistory(
    historyMap: {},
    p: PlayerFixtureHistory,
    fixture: Fixture
  ): void {
    p.history = [
      new FixtureHistory({
        fixtureId: fixture.id,
        date: fixture.createdAt
      })
    ]
    historyMap[p.id] = p
  }

  private updateHistory(
    historyMap: {},
    p: PlayerFixtureHistory,
    fixture: Fixture
  ): void {
    historyMap[p.id].history.push({
      fixtureId: fixture.id,
      date: fixture.createdAt
    })
  }
}
