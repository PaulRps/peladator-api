import {Squad, SquadId} from 'src/modules/squad/domain/squad'

export const SQUAD_OUT_PORT = 'SQUAD_OUT_PORT'
export interface SquadOutPort {
  getOne(id: SquadId): Promise<Squad | null>
  create(squad: Squad): Promise<SquadId>
  update(squad: Squad): Promise<void>
  delete(id: SquadId): Promise<void>
}
