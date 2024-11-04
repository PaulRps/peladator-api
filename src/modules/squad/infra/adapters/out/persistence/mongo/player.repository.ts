import {Injectable} from '@nestjs/common'
import {InjectModel} from '@nestjs/mongoose'
import {Model, Types} from 'mongoose'
import {
  Player,
  PlayerDocument
} from '../../../../config/persistence/mongo/player.document'
import {
  PlayerForFixture,
  PlayerForFixtureDocument
} from 'src/modules/squad/infra/config/persistence/mongo/player-for-fixture.document'

@Injectable()
export class PlayerRepository {
  constructor(
    @InjectModel(Player.name)
    private readonly playerModel: Model<PlayerDocument>,
    @InjectModel(PlayerForFixture.name)
    private readonly playerForFixtureModel: Model<PlayerForFixtureDocument>
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

  saveForFixture(player: PlayerForFixture): Promise<void> {
    return this.playerForFixtureModel.create(player).then(() => {})
  }

  getPlayersForFixture(squadId: string): Promise<PlayerForFixtureDocument[]> {
    return this.playerForFixtureModel
      .find({squadId: new Types.ObjectId(squadId)})
      .exec()
  }

  existsPlayerForFixture(playerId: string, squadId: string): Promise<boolean> {
    return this.playerForFixtureModel
      .exists({
        squadId: new Types.ObjectId(squadId),
        _id: new Types.ObjectId(playerId)
      })
      .then((exists) => Boolean(exists))
  }
}
