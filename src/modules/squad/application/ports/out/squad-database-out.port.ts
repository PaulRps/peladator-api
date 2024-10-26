import {Fixture} from 'src/modules/squad/domain/fixture'
import {Squad, SquadId} from 'src/modules/squad/domain/squad'

export const SQUAD_DATABASE_OUT_PORT = 'SQUAD_DATABASE_OUT_PORT'
export interface SquadDatabaseOutPort {
  getOne(id: SquadId): Promise<Squad | null>
  create(squad: Squad): Promise<SquadId>
  update(squad: Squad): Promise<void>
  delete(id: SquadId): Promise<void>
  createFixture(fixture: Fixture): Promise<Fixture>
  exists(squadId: SquadId): Promise<boolean>
}
