import {Module} from '@nestjs/common'
import {SquadModule} from './modules/squad/squad.module'

@Module({
  imports: [SquadModule]
})
export class AppModule {}
