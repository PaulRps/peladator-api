import {Prop, Schema, SchemaFactory} from '@nestjs/mongoose'
import {HydratedDocument, Types} from 'mongoose'

export type PaymentDocument = HydratedDocument<Payment>

@Schema({
  collection: 'payments',
  _id: false
})
export class Payment {
  @Prop()
  _id: Types.ObjectId

  @Prop()
  squadId: Types.ObjectId

  @Prop()
  monthlyFee: number

  @Prop()
  dailyFee: number

  @Prop()
  customersMonthlyPaid: string[]

  @Prop({
    type: Object
  })
  customersDailyPaid: {}

  @Prop()
  month: number

  @Prop()
  year: number

  constructor({
    _id,
    squadId,
    month,
    year,
    monthlyFee,
    dailyFee,
    customersMonthlyPaid = [],
    customersDailyPaid = []
  }: Partial<Payment> = {}) {
    this._id = _id
    this.squadId = squadId
    this.month = month
    this.year = year
    this.monthlyFee = monthlyFee
    this.dailyFee = dailyFee
    this.customersMonthlyPaid = customersMonthlyPaid
    this.customersDailyPaid = customersDailyPaid
  }
}

export const PaymentSchema = SchemaFactory.createForClass(Payment)
