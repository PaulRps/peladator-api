import {Body, Controller, Get, HttpCode, Post, Put, Query} from '@nestjs/common'
import {PaymentRestInPort} from 'src/modules/payment/application/ports/in/payment-rest-in.port'
import {PaymentRestService} from 'src/modules/payment/application/services/payment-rest.service'
import {Payment} from 'src/modules/payment/domain/payment'

@Controller('v1/payment')
export class PaymentRestInAdapter implements PaymentRestInPort {
  constructor(private readonly paymentService: PaymentRestService) {}

  @Post()
  @HttpCode(201)
  create(@Body() payment: Payment): Promise<Payment> {
    return this.paymentService.create(payment)
  }

  @Get()
  @HttpCode(200)
  getByMonth(
    @Query('month') month: number,
    @Query('squadId') squadId: string
  ): Promise<Payment | null> {
    return this.paymentService.getPaymentByDate(month, squadId)
  }

  @Get('all')
  @HttpCode(200)
  getAll(@Query('squadId') squadId: string): Promise<Payment[]> {
    return this.paymentService.getAll(squadId)
  }

  @Put()
  @HttpCode(200)
  update(@Body() payment: Payment): Promise<void> {
    return this.paymentService.update(payment)
  }
}
