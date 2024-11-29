import {Player} from './player'

export class PlayerForFixture extends Player {
  sequence: number

  constructor({
    id,
    name,
    position,
    level,
    sequence
  }: {
    id?: string
    name?: string
    position?: string
    level?: number
    sequence?: number
  } = {}) {
    super({id, name, position, level})
    this.sequence = sequence
  }
}
