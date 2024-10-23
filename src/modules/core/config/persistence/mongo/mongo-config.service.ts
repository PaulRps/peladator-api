import {Injectable} from '@nestjs/common'
import {ConfigService} from '@nestjs/config'

@Injectable()
export class MongoConfigService {
  constructor(private readonly configService: ConfigService) {}

  get mongoUri(): string {
    const uri = this.configService.get<string>(
      'mongo_config.mongoUri',
    )
    console.log('mongoUri', uri) 
    
    return uri
  }
}
