import {MongooseModule} from '@nestjs/mongoose'
import {Payment, PaymentSchema} from './payment.document'
import {Module} from '@nestjs/common'

const mongoSchemas = MongooseModule.forFeature([
  {name: Payment.name, schema: PaymentSchema}
])

@Module({
  imports: [mongoSchemas],
  exports: [mongoSchemas]
})
export class MongoConfigModule {}
