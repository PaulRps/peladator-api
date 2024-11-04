import {
  Body,
  Controller,
  Delete,
  Get,
  HttpCode,
  Param,
  Post,
  Put,
  Query
} from '@nestjs/common'
import {PlayerRestInPort} from 'src/modules/squad/application/ports/in/player-rest-in.port'
import {PlayerRestService} from 'src/modules/squad/application/services/player-rest.service'
import {Player, PlayerId} from 'src/modules/squad/domain/player'

@Controller('v1/player')
export class PlayerRestInAdapter implements PlayerRestInPort {
  constructor(private readonly playerService: PlayerRestService) {}

  @HttpCode(201)
  @Post()
  create(@Body() player: Player): Promise<Player> {
    return <any>this.playerService.create(player)
  }

  @HttpCode(200)
  @Get()
  getOne(@Query('id') id: PlayerId): Promise<Player> {
    return this.playerService.getOne(id)
  }

  @HttpCode(200)
  @Get('filter/:squadId')
  getBy(@Param('squadId') squadId: string): Promise<Player[]> {
    return this.playerService.getBy(squadId)
  }

  @HttpCode(200)
  @Put()
  update(@Body() player: Player): Promise<void> {
    return this.playerService.update(player)
  }

  @HttpCode(204)
  @Delete(':id')
  delete(@Param('id') id: PlayerId): Promise<void> {
    return this.playerService.delete(id)
  }

  @HttpCode(200)
  @Get('positions')
  getPlayerPositions(): Promise<string[]> {
    return this.playerService.getPlayerPositions()
  }

  @HttpCode(201)
  @Post('for-fixture')
  savePlayerForFixture(@Body() player: Player): Promise<void> {
    return this.playerService.savePlayerForFixture(player)
  }

  @HttpCode(200)
  @Get('for-fixture')
  getPlayersForFixture(@Query('squadId') squadId: string): Promise<Player[]> {
    return this.playerService.getPlayersForFixture(squadId)
  }
}
