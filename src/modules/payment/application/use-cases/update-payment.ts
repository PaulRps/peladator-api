import {Inject, Injectable} from '@nestjs/common'
import {PaymentDatabaseOutPort} from '../ports/out/payment-database-out.port'
import {Payment} from '../../domain/payment'

@Injectable()
export class UpdatePayment {
  constructor(
    @Inject(PaymentDatabaseOutPort.name)
    private readonly paymentDatabaseOutPort: PaymentDatabaseOutPort
  ) {}

  execute(payment: Payment): Promise<void> {
    return this.paymentDatabaseOutPort.update(payment)
  }
}
