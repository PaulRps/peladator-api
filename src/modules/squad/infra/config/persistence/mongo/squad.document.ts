import {Prop, Schema, SchemaFactory} from '@nestjs/mongoose'
import {HydratedDocument, Types} from 'mongoose'

export type SquadDocument = HydratedDocument<Squad>
@Schema({
  collection: 'squads',
  _id: false
})
export class Squad {
  @Prop()
  _id: Types.ObjectId

  @Prop()
  name: string

  @Prop()
  players?: Types.ObjectId[]

  constructor({
    id = null,
    name,
    players
  }: {
    id?: Types.ObjectId
    name: string
    players?: Types.ObjectId[]
  }) {
    this.name = name
    this.players = players
    this._id = id
  }
}

export const SquadSchema = SchemaFactory.createForClass(Squad)
