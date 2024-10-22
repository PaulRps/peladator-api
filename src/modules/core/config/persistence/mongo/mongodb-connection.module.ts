import {Module} from '@nestjs/common'
import {MongooseModule} from '@nestjs/mongoose'
import {MongoConfigService} from './mongo-config.service'
import * as mongoose from 'mongoose'
import { ConfigurationModule } from './config.module'

mongoose.set('debug', true)
@Module({
  imports: [
    MongooseModule.forRootAsync({
      imports: [ConfigurationModule],
      useFactory: async (configService: MongoConfigService) => ({
        uri: configService.mongoUri
      }),
      inject: [MongoConfigService]
    })
  ]
})
export class MongoDbConnectionModule {}
