-- local cacheKey = KEYS[1] --获取缓存键
-- local value = ARGV[1] -- 获取参数值
local a = KEYS[1]
local b = ARGV[1]
local c = ARGV[2]
-- 使用 KEYS[1] 来存储调试信息
redis.call('SET', a, b+c)
return redis.call('GET', a)
-- 1.注释
-- 这是一条单行注释
--[[
这是一个多行注释
可以跨越多行
]]

-- 2.变量：变量在Lua中无需显式声明类型。使用local关键字创建局部变量，全局变量直接声明。
-- 3.数据类型：基本数据类型包括整数、浮点数、字符串、布尔值和nil。表是一种非常灵活的数据结构
-- 4.控制结构
--  4.1条件语句：使用if、else和elseif来实现条件分支
--[[
if age < 18 then
 print("未成年")
elseif age >= 18 and age < 65 then
 print("成年")
else
 print("老年")
end
]]
--  4.2循环结构：Lua支持for循环、while循环和repeat...until循环。
--[[
for i = 1, 5 do
    print(i)
end

local count = 0
while count < 3 do
    print("循环次数: " .. count)
    count = count + 1
end
repeat
        print("至少执行一次")
until count > 5
]]
-- 5.函数在Lua中使用function关键字定义，可以接受参数并返回值。
--[[
function add(a, b)
    return a + b
end

local result = add(2, 3)
print(result)
]]
-- 6.表（Table）在Lua中以键值对的形式存储数据，用花括号{}定义，表可以包含键值对，键和值可以是任何数据类型。
--[[
local person = {name = "John", age = 30}
print(person.name)
print(person["age"])
]]
-- 7.模块，Lua支持模块化编程，允许将相关功能封装在独立的模块中，并通过require关键字加载它们。示例略显复杂，请参考Lua模块的标准用法以获得详细示例。
-- 8.字符串操作：Lua提供了许多字符串处理函数，例如string.sub用于截取子串，string.find用于查找字符串中的子串等。
--[[
local text = "Lua programming"
local sub = string.sub(text, 1, 3)
print(sub) -- 输出 "Lua"
]]
-- 9.错误处理：Lua提供了pcall和xpcall函数，用于捕获和报告错误。
--[[
local function error_func()
    error("An error occurred")
end

local status, result = pcall(error_func)
if not status then
    print("Error: " .. result)
end
]]
-- 10.标准库：Lua提供了丰富的标准库，包括正则表达式、网络变成、数学、文本、文件、日期和时间等。你可以通过内置的模块来使用这些功能，如io、socket等

