import {Module} from '@nestjs/common'
import {PersistenceModule} from './infra/adapters/out/persistence/persistence.module'
import {CoreModule} from '../core/core.module'
import {PaymentRestService} from './application/services/payment-rest.service'
import {CreatePayment} from './application/use-cases/create-payment'
import {UpdatePayment} from './application/use-cases/update-payment'
import {PaymentRestInAdapter} from './infra/adapters/in/payment-rest-in.adapter'
import {PaymentDatabaseOutPort} from './application/ports/out/payment-database-out.port'
import {PaymentMongoOutAdapter} from './infra/adapters/out/persistence/mongo/payment-mongo-out.adapter'
import {GetPaymentBySquadAndMonth} from './application/use-cases/get-payment-by-squad-and-date'
import {GetAllPayments} from './application/use-cases/get-all'

@Module({
  imports: [CoreModule, PersistenceModule],
  controllers: [PaymentRestInAdapter],
  providers: [
    PaymentRestService,
    CreatePayment,
    UpdatePayment,
    GetPaymentBySquadAndMonth,
    GetAllPayments,
    {
      provide: PaymentDatabaseOutPort.name,
      useClass: PaymentMongoOutAdapter
    }
  ]
})
export class PaymentModule {}
