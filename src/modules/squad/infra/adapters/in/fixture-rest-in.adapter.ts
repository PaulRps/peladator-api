import {Body, Controller, Get, HttpCode, Param, Post} from '@nestjs/common'
import {FixtureRestInPort} from 'src/modules/squad/application/ports/in/fixture-rest-in.port'
import {FixtureRestService} from 'src/modules/squad/application/services/fixture-rest.service'
import {Fixture} from 'src/modules/squad/domain/fixture'
import {FixtureCriteria} from 'src/modules/squad/domain/fixture-criteria'
import {SquadId} from 'src/modules/squad/domain/squad'

@Controller('v1/fixture')
export class FixtureRestInAdapter implements FixtureRestInPort {
  constructor(private readonly fixtureRestService: FixtureRestService) {}

  @HttpCode(201)
  @Post()
  createFixture(@Body() fixture: FixtureCriteria): Promise<Fixture> {
    return this.fixtureRestService.createFixture(fixture)
  }

  @HttpCode(200)
  @Get(':squadId/latest')
  getLastFixture(@Param('squadId') squadId: SquadId): Promise<Fixture> {
    return this.fixtureRestService.getLatestFixture(squadId)
  }
}
