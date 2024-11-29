import {Injectable} from '@nestjs/common'
import {
  Fixture,
  FixtureDocument
} from '../../../../config/persistence/mongo/fixture.document'
import {Model, Types} from 'mongoose'
import {InjectModel} from '@nestjs/mongoose'
import {FilterFixture} from 'src/modules/squad/domain/models/filter-fixture'

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

  filterFixtures({
    squadId,
    startDate,
    endDate
  }: FilterFixture): Promise<FixtureDocument[]> {
    const query: any = {squadId: new Types.ObjectId(squadId)}

    if (startDate && endDate) {
      query.createdAt = {$gte: new Date(startDate), $lte: new Date(endDate)}
    }

    return this.fixtureModel.find(query).exec()
  }

  update(fixture: Fixture): Promise<void> {
    return this.fixtureModel
      .updateOne({_id: fixture._id}, fixture)
      .exec()
      .then(() => {})
  }

  delete(id: string): Promise<void> {
    return this.fixtureModel
      .deleteOne({_id: new Types.ObjectId(id)})
      .exec()
      .then(() => {})
  }
}
