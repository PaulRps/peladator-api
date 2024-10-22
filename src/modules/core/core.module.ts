import { Module } from "@nestjs/common";
import { MongoDbConnectionModule } from "./config/persistence/mongo/mongodb-connection.module";
import { ConfigurationModule } from "./config/persistence/mongo/config.module";

@Module({
  imports: [MongoDbConnectionModule, ConfigurationModule],
  exports: [MongoDbConnectionModule, ConfigurationModule],
})
export class CoreModule {}