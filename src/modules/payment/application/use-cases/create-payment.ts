import {Inject, Injectable} from '@nestjs/common'
import {Payment} from '../../domain/payment'
import {PaymentDatabaseOutPort} from '../ports/out/payment-database-out.port'

@Injectable()
export class CreatePayment {
  constructor(
    @Inject(PaymentDatabaseOutPort.name)
    private readonly paymentDatabaseOutPort: PaymentDatabaseOutPort
  ) {}

  execute(payment: Payment): Promise<Payment> {
    return this.paymentDatabaseOutPort.create(payment).then((id) => {
      payment.id = id
      return payment
    })
  }
}
