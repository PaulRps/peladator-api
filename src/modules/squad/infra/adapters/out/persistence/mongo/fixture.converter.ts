import {Injectable} from '@nestjs/common'
import {Types} from 'mongoose'
import {Fixture} from 'src/modules/squad/domain/fixture'
import {LineUp} from 'src/modules/squad/domain/lineup'
import {PlayerForFixture} from 'src/modules/squad/domain/player-for-fixture'
import * as FixtureDocument from 'src/modules/squad/infra/config/persistence/mongo/fixture.document'

@Injectable()
export class FixtureConverter {
  toFixture(fixture: FixtureDocument.Fixture): Fixture {
    return new Fixture({
      id: fixture._id.toString(),
      squadId: fixture.squadId.toString(),
      createdAt: fixture.createdAt.toISOString(),
      lineUps: fixture.lineUps.map((lineUp) => {
        return new LineUp({
          name: lineUp.name,
          level: lineUp.level,
          players: lineUp.players.map((player) => {
            return new PlayerForFixture({
              id: player.id.toString(),
              sequence: player.sequence
            })
          })
        })
      })
    })
  }

  toFixtureDocument(fixture: Fixture): FixtureDocument.Fixture {
    return new FixtureDocument.Fixture({
      _id: fixture?.id ? new Types.ObjectId(fixture.id) : null,
      squadId: new Types.ObjectId(fixture.squadId),
      createdAt: fixture?.createdAt ? new Date(fixture.createdAt) : null,
      lineUps: fixture?.lineUps.map((lineUp) => {
        return {
          name: lineUp.name,
          level: lineUp.level,
          players: lineUp.players.map((player) => {
            return {
              id: new Types.ObjectId(player.id),
              sequence: player.sequence
            }
          })
        }
      })
    })
  }
}
