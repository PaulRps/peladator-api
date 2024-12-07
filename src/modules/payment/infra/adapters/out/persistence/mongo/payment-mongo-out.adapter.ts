import {Injectable} from '@nestjs/common'
import {PaymentDatabaseOutPort} from 'src/modules/payment/application/ports/out/payment-database-out.port'
import {Payment} from 'src/modules/payment/domain/payment'
import {PaymentRepository} from './payment.repository'
import {PaymentConverter} from './payment.converter'

@Injectable()
export class PaymentMongoOutAdapter implements PaymentDatabaseOutPort {
  constructor(
    private readonly paymentRepository: PaymentRepository,
    private readonly paymentConverter: PaymentConverter
  ) {}

  create(payment: Payment): Promise<string> {
    return this.paymentRepository.save(
      this.paymentConverter.toPaymentDocument(payment)
    )
  }

  getBy(month: number, squadId: string, id: string): Promise<Payment | null> {
    return this.paymentRepository.getBy(month, squadId, id).then((payment) => {
      return payment ? this.paymentConverter.toPayment(payment) : null
    })
  }

  getAll(squadId: string): Promise<Payment[]> {
    return this.paymentRepository.getAll(squadId).then((payments) => {
      return payments.map((payment) => this.paymentConverter.toPayment(payment))
    })
  }

  update(payment: Payment): Promise<void> {
    return this.paymentRepository.update(
      this.paymentConverter.toPaymentDocument(payment)
    )
  }
}
