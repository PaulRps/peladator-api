import {Injectable} from '@nestjs/common'
import {Types} from 'mongoose'
import {Payment} from 'src/modules/payment/domain/payment'
import * as PaymentDocument from 'src/modules/payment/infra/config/persistence/mongo/payment.document'

@Injectable()
export class PaymentConverter {
  toPaymentDocument(from: Payment): PaymentDocument.Payment {
    return new PaymentDocument.Payment({
      id: from?.id ? new Types.ObjectId(from?.id) : null,
      squadId: new Types.ObjectId(from.squadId),
      month: from.month,
      monthlyFee: from.monthlyFee,
      dailyFee: from.dailyFee,
      customersMonthlyPaid: from?.customersMonthlyPaid,
      customersDailyPaid: from?.customersDailyPaid
    })
  }

  toPayment(from: PaymentDocument.Payment): Payment {
    return new Payment({
      id: from._id.toString(),
      squadId: from.squadId.toString(),
      monthlyFee: from.monthlyFee,
      dailyFee: from.dailyFee,
      customersMonthlyPaid: from.customersMonthlyPaid,
      customersDailyPaid: from.customersDailyPaid,
      month: from.month
    })
  }
}
