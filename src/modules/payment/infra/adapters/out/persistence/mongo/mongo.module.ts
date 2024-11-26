import {Module} from '@nestjs/common'
import {MongoConfigModule} from 'src/modules/payment/infra/config/persistence/mongo/mongo-config.module'
import {PaymentRepository} from './payment.repository'
import {PaymentConverter} from './payment.converter'

@Module({
  imports: [MongoConfigModule],
  providers: [PaymentRepository, PaymentConverter],
  exports: [PaymentRepository, PaymentConverter]
})
export class MongoModule {}
