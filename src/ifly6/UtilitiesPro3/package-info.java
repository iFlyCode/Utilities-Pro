/* Copyright (c) 2017 ifly6
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */

/**
 * Package contains all classes necessary to run UtilitiesPro.
 *
 * <p>
 * Utilities Pro was built not as an exercise in programming elegance and object-oriented programming, but rather as a
 * 'get it to work' program. Pretty much everything in the entire program is static with minimal non-static classes.
 * Furthermore, pretty much all classes are extended from the intial class. The reasons for doing so, given my lack of
 * programming expertise and only care for getting it to work, are obvious from the analytics of (1) hating long files
 * and (2) being annoyed at having to pass objects around. The extensions are all there to basically pretend that the
 * entire program is a massive set of methods all within the same class.
 * </p>
 *
 * <p>
 * Naturally, that is probably not a good way of looking at programming. For the archetypical object-oriented
 * programming language, it is probably not the best thing to treat it as if it were a gigantic massive file. But since
 * the entire program is built around executing functions all associated with the massive number of buttons in the
 * program and simply put, doing it cheap and fast, there are corners cut. For example, if I had the knowledge I have
 * today, I would have never built <code>ExecEngine</code> as an extension from <code>Utilities_Pro</code> or
 * <code>ScriptCommands</code> as a static system. I would have rather split them into files that would be instantiated
 * with properties set and executed much like the classes which manage files in Communiqué and iFly Region Protection.
 * </p>
 */
package ifly6.UtilitiesPro3;