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
import {SquadInPort} from 'src/modules/squad/application/ports/in/squad-in.port'
import {SquadRestService} from 'src/modules/squad/application/services/squad-rest.service'
import {Squad} from 'src/modules/squad/domain/squad'

@Controller('squad')
export class SquadRestInAdapter implements SquadInPort {
  constructor(private readonly squadService: SquadRestService) {}

  @HttpCode(200)
  @Get(':id')
  async getOne(@Param('id') id: string): Promise<Squad | null> {
    return await this.squadService.getOne(id)
  }

  @HttpCode(201)
  @Post()
  create(@Body() squad: Squad): Promise<string> {
    return this.squadService.create(squad)
  }

  @HttpCode(200)
  @Put()
  update(@Body() squad: Squad): Promise<void> {
    return this.squadService.update(squad)
  }

  @HttpCode(204)
  @Delete(':id')
  delete(@Param('id') id: string): Promise<void> {
    return this.squadService.delete(id)
  }
}
