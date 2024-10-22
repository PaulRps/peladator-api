import {Injectable} from '@nestjs/common'
import {InjectModel} from '@nestjs/mongoose'
import {Model, Types} from 'mongoose'
import {Player, PlayerDocument} from './player.document'

@Injectable()
export class PlayerRepository {
  constructor(
    @InjectModel(Player.name) private playerModel: Model<PlayerDocument>
  ) {}

  getOne(id: string): Promise<PlayerDocument | null> {
    return this.playerModel.findOne({_id: new Types.ObjectId(id)}).exec()
  }

  getBySquadId(squadId: string): Promise<PlayerDocument[]> {
    return this.playerModel.find({squadId: new Types.ObjectId(squadId)}).exec()
  }

  save(player: Player): Promise<string> {
    if (!player._id) player._id = new Types.ObjectId()

    return this.playerModel
      .create(player)
      .then((player) => player._id.toString())
  }

  update(player: Player): Promise<void> {
    return this.playerModel
      .updateOne({_id: player._id}, player)
      .exec()
      .then(() => {})
  }

  delete(id: string): Promise<void> {
    return this.playerModel
      .deleteOne({_id: new Types.ObjectId(id)})
      .exec()
      .then(() => {})
  }

  deleteBySquadId(id: string): Promise<void> {
    return this.playerModel
      .deleteMany({squadId: new Types.ObjectId(id)})
      .exec()
      .then(() => {})
  }
}
