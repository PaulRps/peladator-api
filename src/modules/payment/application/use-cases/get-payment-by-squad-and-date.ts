import {Inject, Injectable, Module} from '@nestjs/common'
import {Payment} from '../../domain/payment'
import {PaymentDatabaseOutPort} from '../ports/out/payment-database-out.port'

@Injectable()
export class GetPaymentBySquadAndMonth {
  constructor(
    @Inject(PaymentDatabaseOutPort.name)
    private readonly paymentDatabaseOutPort: PaymentDatabaseOutPort
  ) {}

  execute(month: number, squadId: string): Promise<Payment | null> {
    return this.paymentDatabaseOutPort.getByMonth(month, squadId)
  }
}
