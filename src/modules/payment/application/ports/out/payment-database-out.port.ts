import {Payment} from 'src/modules/payment/domain/payment'

export interface PaymentDatabaseOutPort {
  create(payment: Payment): Promise<string>
  getBy(month: number, squadId: string, id: string): Promise<Payment | null>
  getAll(squadId: string): Promise<Payment[]>
  update(payment: Payment): Promise<void>
}

export namespace PaymentDatabaseOutPort {
  export const name = 'PaymentDatabaseOutPort'
}
