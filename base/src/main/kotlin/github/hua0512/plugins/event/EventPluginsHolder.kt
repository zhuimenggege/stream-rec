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

package github.hua0512.plugins.event

/**
 * Singleton class that holds all event plugins and manages their lifecycle
 * @author hua0512
 * @date : 2024/3/17 22:57
 */
object EventPluginsHolder {

  private val _plugins = mutableListOf<BaseEventPlugin>()

  fun registerPlugin(plugin: BaseEventPlugin) {
    _plugins.add(plugin)
  }

  fun registerPlugins(vararg plugins: BaseEventPlugin) {
    _plugins.addAll(plugins)
  }

  fun unregisterPlugin(plugin: BaseEventPlugin) {
    _plugins.remove(plugin)
  }

  fun getPlugins(): List<BaseEventPlugin> {
    return _plugins
  }

  fun clearPlugins() {
    _plugins.forEach { it.cleanUp() }
    _plugins.clear()
  }
}