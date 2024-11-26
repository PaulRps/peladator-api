export class Payment {
  id: string
  squadId: string
  month: number
  monthlyFee: number
  dailyFee: number
  customersMonthlyPaid: string[]
  customersDailyPaid: {}

  constructor({
    id,
    squadId,
    month,
    monthlyFee,
    dailyFee,
    customersMonthlyPaid,
    customersDailyPaid
  }: {
    id: string
    squadId: string
    month: number
    monthlyFee: number
    dailyFee: number
    customersMonthlyPaid: string[]
    customersDailyPaid: {}
  }) {
    this.id = id
    this.squadId = squadId
    this.month = month
    this.monthlyFee = monthlyFee
    this.dailyFee = dailyFee
    this.customersMonthlyPaid = customersMonthlyPaid
    this.customersDailyPaid = customersDailyPaid
  }
}
