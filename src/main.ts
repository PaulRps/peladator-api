import { NestFactory } from '@nestjs/core'
import { AppModule } from './app.module'
import { ExpressAdapter } from '@nestjs/platform-express'
import * as express from 'express'
import * as serverless from 'serverless-http'

async function bootstrap(module: any): Promise<any> {
  const app = express()
  const nestApp = await NestFactory.create(module, new ExpressAdapter(app))

  nestApp.enableCors()
  nestApp.setGlobalPrefix('/.netlify/functions/server')
  // await nestApp.listen(3000);
  await nestApp.init()
  return nestApp
}

let cachedHadler: any
const proxyApi = async (module: any, event: any, context: any) => {
  if (!cachedHadler) {
    const app = await bootstrap(module)
    cachedHadler = serverless(app)
  }

  return cachedHadler(event, context)
}

export const handler = async (event: any, context: any) =>
  proxyApi(AppModule, event, context)
