import {Injectable} from '@nestjs/common'
import {CreatePayment} from '../use-cases/create-payment'
import {UpdatePayment} from '../use-cases/update-payment'
import {Payment} from '../../domain/payment'
import {GetPaymentBySquadAndMonth} from '../use-cases/get-payment-by-squad-and-date'
import {GetAllPayments} from '../use-cases/get-all'

@Injectable()
export class PaymentRestService {
  constructor(
    private readonly createPayment: CreatePayment,
    private readonly updatePayment: UpdatePayment,
    private readonly getPaymentByDateAndSquadId: GetPaymentBySquadAndMonth,
    private readonly getAllPayment: GetAllPayments
  ) {}

  create(payment: Payment): Promise<Payment> {
    return this.createPayment.execute(payment)
  }

  update(payment: Payment): Promise<void> {
    return this.updatePayment.execute(payment)
  }

  getPaymentByDate(month: number, squadId: string): Promise<Payment | null> {
    return this.getPaymentByDateAndSquadId.execute(month, squadId)
  }

  getAll(squadId: string): Promise<Payment[]> {
    return this.getAllPayment.execute(squadId)
  }
}
