import { Module } from "@nestjs/common";
import configuration from "./configuration";
import * as Joi from 'joi'
import { ConfigModule } from "@nestjs/config";
import { MongoConfigService } from "./mongo-config.service";

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,
      cache: true,
      load: [configuration],
      validationSchema: Joi.object({
        MONGO_URI: Joi.string().required().messages({
          'any.required':
            'MONGO_URI not set. If running e2e test set variable value in test.env.js file'
        })
      })
    })
  ],
  providers: [MongoConfigService],
  exports: [MongoConfigService]
})
export class ConfigurationModule {}