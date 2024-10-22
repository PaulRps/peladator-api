import {Prop, Schema, SchemaFactory} from '@nestjs/mongoose'
import {HydratedDocument, Types} from 'mongoose'

export type PlayerDocument = HydratedDocument<Player>
@Schema({
  collection: 'players',
  _id: false
})
export class Player {
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

export const PlayerSchema = SchemaFactory.createForClass(Player)
