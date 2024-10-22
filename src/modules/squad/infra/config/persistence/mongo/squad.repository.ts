import {Injectable} from '@nestjs/common'
import {InjectModel} from '@nestjs/mongoose'
import {Model, Types} from 'mongoose'
import {Squad, SquadDocument} from './squad.document'

@Injectable()
export class SquadRepository {
  constructor(
    @InjectModel(Squad.name) private squadModel: Model<SquadDocument>
  ) {}

  getAll(): Promise<SquadDocument[]> {
    return this.squadModel.find().exec()
  }

  getById(squadId: string): Promise<SquadDocument> | null {
    return this.squadModel.findById(new Types.ObjectId(squadId)).exec()
  }

  save(squad: Squad): Promise<string> {
    if (!squad._id) squad._id = new Types.ObjectId()

    return this.squadModel.create(squad).then((doc) => doc._id.toString())
  }

  delete(squadId: string): Promise<void> {
    return this.squadModel
      .deleteOne({_id: new Types.ObjectId(squadId)})
      .exec()
      .then(() => {})
  }

  update(squad: Squad): Promise<void> {
    return this.squadModel
      .updateOne({_id: squad._id}, squad)
      .exec()
      .then(() => {})
  }
}
