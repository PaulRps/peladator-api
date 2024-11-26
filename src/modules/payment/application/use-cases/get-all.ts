import {Inject, Injectable} from '@nestjs/common'
import {Payment} from '../../domain/payment'
import {PaymentDatabaseOutPort} from '../ports/out/payment-database-out.port'

@Injectable()
export class GetAllPayments {
  constructor(
    @Inject(PaymentDatabaseOutPort.name)
    private readonly paymentDatabaseOutPort: PaymentDatabaseOutPort
  ) {}

  execute(squadId: string): Promise<Payment[]> {
    return this.paymentDatabaseOutPort.getAll(squadId)
  }
}
