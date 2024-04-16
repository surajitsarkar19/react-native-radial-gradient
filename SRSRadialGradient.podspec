require 'json'
version = JSON.parse(File.read('./package.json'))["version"]

Pod::Spec.new do |s|

  s.name            = "SRSRadialGradient"
  s.version         = version
  s.homepage        = "https://github.com/surajitsarkar19/react-native-radial-gradient"
  s.summary         = "A <RadialGradient /> component for react-native"
  s.license         = "MIT"
  s.author          = { "Surajit Sarkar" => "surajitsarkar19@gmail.com" }
  s.ios.deployment_target = '7.0'
  s.tvos.deployment_target = '9.0'
  s.source          = { :git => "https://github.com/surajitsarkar19/react-native-radial-gradient.git", :tag => "v#{s.version}" }
  s.source_files    = 'ios/**/*.{h,m}'
  s.preserve_paths  = "**/*.js"
  s.frameworks = 'UIKit', 'QuartzCore', 'Foundation'

  s.dependency 'React'

end
