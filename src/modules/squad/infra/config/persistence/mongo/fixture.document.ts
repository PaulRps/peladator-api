import {Prop, Schema, SchemaFactory} from '@nestjs/mongoose'
import {HydratedDocument, Types} from 'mongoose'
import {LineUp} from 'src/modules/squad/domain/models/lineup'

export type FixtureDocument = HydratedDocument<Fixture>

@Schema({collection: 'fixtures', _id: false})
export class Fixture {
  @Prop()
  _id: Types.ObjectId

  @Prop()
  squadId: Types.ObjectId

  @Prop({
    type: Date,
    default: Date.now
  })
  createdAt: Date

  @Prop()
  lineUps: LineUpPartial[]

  constructor({
    _id = null,
    squadId = null,
    createdAt = null,
    lineUps
  }: {
    _id?: Types.ObjectId
    squadId?: Types.ObjectId
    createdAt?: Date
    lineUps?: LineUpPartial[]
  } = {}) {
    this._id = _id
    this.squadId = squadId
    this.createdAt = createdAt
    this.lineUps = lineUps
  }
}

export type LineUpPartial = Pick<LineUp, 'name' | 'level'> & {
  players: PlayerForFixturePartial[]
}
export type PlayerForFixturePartial = {
  id: Types.ObjectId | string
  sequence: number
}
export const FixtureSchema = SchemaFactory.createForClass(Fixture)
