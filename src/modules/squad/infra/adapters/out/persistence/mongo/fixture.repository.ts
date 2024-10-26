import {Injectable} from '@nestjs/common'
import {
  Fixture,
  FixtureDocument
} from '../../../../config/persistence/mongo/fixture.document'
import {Model, Types} from 'mongoose'
import {InjectModel} from '@nestjs/mongoose'

@Injectable()
export class FixtureRepository {
  constructor(
    @InjectModel(Fixture.name) private fixtureModel: Model<FixtureDocument>
  ) {}

  save(fixture: Fixture): Promise<FixtureDocument> {
    if (!fixture._id) fixture._id = new Types.ObjectId()

    return this.fixtureModel.create(fixture)
  }

  getLatestFixture(squadId: Types.ObjectId): Promise<FixtureDocument | null> {
    return this.fixtureModel
      .findOne({squadId})
      .sort({createdAt: -1})
      .limit(1)
      .exec()
  }
}
