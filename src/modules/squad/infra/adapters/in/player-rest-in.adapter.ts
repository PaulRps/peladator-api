import {
  Body,
  Controller,
  Delete,
  Get,
  HttpCode,
  Param,
  Post,
  Put
} from '@nestjs/common'
import {PlayerInPort} from 'src/modules/squad/application/ports/in/player-in.port'
import {PlayerRestService} from 'src/modules/squad/application/services/player-rest.service'
import {Player, PlayerId} from 'src/modules/squad/domain/player'

@Controller('player')
export class PlayerRestInAdapter implements PlayerInPort {
  constructor(private readonly playerService: PlayerRestService) {}

  @HttpCode(201)
  @Post()
  create(@Body() player: Player): Promise<PlayerId> {
    return this.playerService.create(player)
  }

  @HttpCode(200)
  @Get(':id')
  getOne(@Param('id') id: PlayerId): Promise<Player> {
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
}
