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

  constructor({
    id = null,
    squadId,
    month,
    monthlyFee,
    dailyFee,
    customersMonthlyPaid = [],
    customersDailyPaid = []
  }: {
    id?: Types.ObjectId
    squadId: Types.ObjectId
    month: number
    monthlyFee: number
    dailyFee: number
    customersMonthlyPaid?: string[]
    customersDailyPaid?: {}
  }) {
    this._id = id
    this.squadId = squadId
    this.month = month
    this.monthlyFee = monthlyFee
    this.dailyFee = dailyFee
    this.customersMonthlyPaid = customersMonthlyPaid
    this.customersDailyPaid = customersDailyPaid
  }
}

export const PaymentSchema = SchemaFactory.createForClass(Payment)
