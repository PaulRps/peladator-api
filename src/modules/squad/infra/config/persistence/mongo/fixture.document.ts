import {Prop, Schema, SchemaFactory} from '@nestjs/mongoose'
import {HydratedDocument, Types} from 'mongoose'
import {LineUp} from 'src/modules/squad/domain/lineup'

export type FixtureDocument = HydratedDocument<Fixture>

if (!process.env.FIXTURE_EXPIRE_TIME)
  throw new Error('FIXTURE_EXPIRE_TIME is not defined')

@Schema({collection: 'fixtures', _id: false})
export class Fixture {
  @Prop()
  _id: Types.ObjectId

  @Prop()
  squadId: Types.ObjectId

  @Prop({
    type: Date,
    expires: process.env.FIXTURE_EXPIRE_TIME,
    default: Date.now
  })
  createdAt: Date

  @Prop()
  squads: LineUp[]

  constructor({
    _id = null,
    squadId = null,
    createdAt = null,
    squads
  }: {
    _id?: Types.ObjectId
    squadId?: Types.ObjectId
    createdAt?: Date
    squads?: LineUp[]
  } = {}) {
    this._id = _id
    this.squadId = squadId
    this.createdAt = createdAt
    this.squads = squads
  }
}

export const FixtureSchema = SchemaFactory.createForClass(Fixture)
