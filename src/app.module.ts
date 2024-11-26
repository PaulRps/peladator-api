import {Module} from '@nestjs/common'
import {SquadModule} from './modules/squad/squad.module'
import {PaymentModule} from './modules/payment/payment.module'

@Module({
  imports: [SquadModule, PaymentModule]
})
export class AppModule {}
