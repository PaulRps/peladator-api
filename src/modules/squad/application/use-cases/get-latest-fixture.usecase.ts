import {Inject, Injectable} from '@nestjs/common'
import {Fixture} from '../../domain/models/fixture'
import {FixtureDatabaseOutPort} from '../ports/out/fixture-database-out.port'
import {FilterPlayers, FilterPlayersForFixture} from './filter-players.usecase'
import {PlayerForFixture} from '../../domain/models/player-for-fixture'

@Injectable()
export class GetLatestFixture {
  constructor(
    @Inject(FixtureDatabaseOutPort.name)
    private readonly fixtureDatabaseOutPort: FixtureDatabaseOutPort,
    private readonly filterPlayers: FilterPlayers
  ) {}

  execute(squadId: string): Promise<Fixture | null> {
    return this.fixtureDatabaseOutPort
      .getLatestFixture(squadId)
      .then(async (fixture) => {
        if (fixture) {
          const lineUps = fixture.lineUps.map(async (lineUp) => {
            const players = await this.filterPlayers.execute(
              new FilterPlayersForFixture({
                squadId,
                ids: lineUp.players.map((player) => player.id)
              })
            )
            const playersMap = players.reduce((map, player) => {
              map[player.id] = player
              return map
            }, {})

            lineUp.players = lineUp.players.map((player) => {
              return new PlayerForFixture({
                id: player.id,
                name: playersMap[player.id].name,
                level: playersMap[player.id].level,
                position: playersMap[player.id].position,
                sequence: player.sequence
              })
            })

            return lineUp
          })

          fixture.lineUps = await Promise.all(lineUps)
          return fixture
        }
      })
  }
}
