/*
 * MIT License
 *
 * Stream-rec  https://github.com/hua0512/stream-rec
 *
 * Copyright (c) 2025 hua0512 (https://github.com/hua0512)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package github.hua0512.repo.config

import github.hua0512.dao.config.EngineConfigDao
import github.hua0512.data.config.engine.EngineConfig
import github.hua0512.data.config.engine.FFMPEG_ENGINE
import github.hua0512.data.config.engine.KOTLIN_ENGINE
import github.hua0512.data.config.engine.STREAMLINK_ENGINE

/**
 * Repository for engine config data
 * @author hua0512
 * @date : 2/15/2025 9:12 PM
 */
interface EngineConfigRepo {

  val dao: EngineConfigDao

  /**
   * Fetch engine configuration by config ID and engine type
   *
   * @param configId The ID of the configuration to fetch
   * @param engineType The type of engine for which to fetch the configuration
   * @return The engine configuration for the specified ID and type
   */
  suspend fun <T : EngineConfig> getEngineConfig(configId: Int, engineType: String): T?

  /**
   * Update engine configuration
   *
   * @param configId The ID of the configuration to update
   * @param config The new engine configuration to update
   * @return The updated engine configuration
   */
  suspend fun <T : EngineConfig> updateEngineConfig(configId: Int, config: T): T


  /**
   * Creates a default engine configuration based on the engine type
   *
   * @param engineType The type of engine for which to create a default configuration
   * @return A default configuration for the specified engine type
   */
  fun createDefaultConfig(engineType: String): EngineConfig {
    return when (engineType) {
      STREAMLINK_ENGINE -> EngineConfig.StreamlinkConfig()
      FFMPEG_ENGINE -> EngineConfig.FFmpegConfig()
      KOTLIN_ENGINE -> EngineConfig.KotlinConfig()
      else -> throw IllegalArgumentException("Unknown engine type: $engineType")
    }
  }

}