import {Injectable} from '@nestjs/common'
import {InjectModel} from '@nestjs/mongoose'
import {Model, Types} from 'mongoose'
import {
  Payment,
  PaymentDocument
} from '../../../../config/persistence/mongo/payment.document'

@Injectable()
export class PaymentRepository {
  constructor(
    @InjectModel(Payment.name)
    private readonly paymentModel: Model<PaymentDocument>
  ) {}

  save(payment: Payment): Promise<string> {
    if (!payment._id) payment._id = new Types.ObjectId()

    return this.paymentModel
      .create(payment)
      .then((payment) => payment._id.toString())
  }

  getBy(month: number, squadId: string, id: string): Promise<Payment | null> {
    const query: any = {}
    if (id) {
      query._id = new Types.ObjectId(id)
    }
    if (squadId) {
      query.squadId = new Types.ObjectId(squadId)
    }
    if (month) {
      query.month = month
    }
    return this.paymentModel.findOne(query).exec()
  }

  getAll(squadId: string): Promise<Payment[]> {
    return this.paymentModel.find({squadId: new Types.ObjectId(squadId)}).exec()
  }

  update(payment: Payment): Promise<void> {
    return this.paymentModel
      .updateOne(
        {
          _id: new Types.ObjectId(payment._id),
          squadId: payment.squadId
        },
        {
          $set: {
            monthlyFee: payment.monthlyFee,
            dailyFee: payment.dailyFee,
            customersMonthlyPaid: payment.customersMonthlyPaid,
            customersDailyPaid: payment.customersDailyPaid
          }
        }
      )
      .exec()
      .then(() => {})
  }
}
