import {Prop, Schema, SchemaFactory} from '@nestjs/mongoose'
import {HydratedDocument, Types} from 'mongoose'

export type PlayerForFixtureDocument = HydratedDocument<PlayerForFixture>

if (!process.env.FIXTURE_EXPIRE_TIME)
  throw new Error('FIXTURE_EXPIRE_TIME is not defined')

@Schema({
  collection: 'players-for-fixture',
  _id: false
})
export class PlayerForFixture {
  @Prop()
  _id: Types.ObjectId

  @Prop()
  squadId: Types.ObjectId

  @Prop()
  name: string

  @Prop()
  position: string

  @Prop()
  level: number

  @Prop({
    type: Date,
    expires: process.env.FIXTURE_EXPIRE_TIME,
    default: Date.now
  })
  createdAt: Date

  constructor({
    id = null,
    name,
    position,
    level,
    squadId
  }: {
    id?: Types.ObjectId
    name: string
    position: string
    level: number
    squadId: Types.ObjectId
  }) {
    this._id = id
    this.name = name
    this.position = position
    this.level = level
    this.squadId = squadId
  }
}

export const PlayerForFixtureSchema =
  SchemaFactory.createForClass(PlayerForFixture)
