#import <UIKit/UIKit.h>
#import <React/RCTView.h>

@interface SRSRadialGradient : RCTView

@property (nullable, nonatomic, strong) NSArray *colors;
@property (nullable, nonatomic, strong) NSArray<NSNumber *> *stops;
@property (nonatomic) CGPoint gradientCenter;
@property (nonatomic) CGFloat radius;

@end
