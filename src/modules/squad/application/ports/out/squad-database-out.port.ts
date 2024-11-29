import {Fixture} from 'src/modules/squad/domain/models/fixture'
import {Squad, SquadId} from 'src/modules/squad/domain/models/squad'

export interface SquadDatabaseOutPort {
  getOne(id: SquadId): Promise<Squad | null>
  create(squad: Squad): Promise<SquadId>
  update(squad: Squad): Promise<void>
  delete(id: SquadId): Promise<void>
  createFixture(fixture: Fixture): Promise<Fixture>
  exists(squadId: SquadId): Promise<boolean>
}

export namespace SquadDatabaseOutPort {
  export const name = 'SquadDatabaseOutPort'
}
