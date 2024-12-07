export class Payment {
  id: string
  squadId: string
  month: number
  year: number
  monthlyFee: number
  dailyFee: number
  customersMonthlyPaid: string[]
  customersDailyPaid: {}

  constructor({
    id,
    squadId,
    month,
    year,
    monthlyFee,
    dailyFee,
    customersMonthlyPaid,
    customersDailyPaid
  }: Partial<Payment> = {}) {
    this.id = id
    this.squadId = squadId
    this.month = month
    this.year = year
    this.monthlyFee = monthlyFee
    this.dailyFee = dailyFee
    this.customersMonthlyPaid = customersMonthlyPaid
    this.customersDailyPaid = customersDailyPaid
  }
}
