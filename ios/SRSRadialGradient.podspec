
Pod::Spec.new do |s|
  s.name         = "SRSRadialGradient"
  s.version      = "1.0.0"
  s.summary      = "SRSRadialGradient"
  s.description  = <<-DESC
                  SRSRadialGradient
                   DESC
  s.homepage     = "https://github.com/surajitsarkar19/react-native-radial-gradient"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/SRSRadialGradient.git", :tag => "master" }
  s.source_files  = "SRSRadialGradient/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  
