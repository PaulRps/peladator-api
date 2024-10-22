import {registerAs} from '@nestjs/config'
import * as env from 'env-var'

export default registerAs('mongo_config', () => ({
  mongoUri: env.get('MONGO_URI').asString()
}))
