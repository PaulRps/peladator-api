import {Payment} from 'src/modules/payment/domain/payment'

export interface PaymentRestInPort {
  create(payment: Payment): Promise<Payment>
  getBy(month: number, squadId: string, id: string): Promise<Payment | null>
  getAll(squadId: string): Promise<Payment[]>
  update(payment: Payment): Promise<void>
}
