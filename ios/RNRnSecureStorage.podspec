
Pod::Spec.new do |s|
  s.name         = "RNRnSecureStorage"
  s.version      = "1.0.0"
  s.summary      = "RNRnSecureStorage"
  s.description  = <<-DESC
                  RNRnSecureStorage
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "Talut Tasgiran" => "talut@tasgiran.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/talut/react-native-secure-storage.git", :tag => "master" }
  s.source_files  = "RNRnSecureStorage/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  